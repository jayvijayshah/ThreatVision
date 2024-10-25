# backend/app.py
from fastapi import FastAPI, WebSocket, UploadFile, File
from fastapi.responses import HTMLResponse
import base64
import httpx
import numpy as np
import cv2

app = FastAPI()

# Replace with your Llama model API endpoint
LLAMA_VISION_API_URL = "http://<llama_vision_model_endpoint>"  # Update this URL

async def send_image_to_llama(image_data: str):
    """
    Sends the base64 encoded image to the Llama Vision model API and gets the response.
    """
    # Prepare the payload for the Llama API
    payload = {
        "image": image_data
    }
    async with httpx.AsyncClient() as client:
        response = await client.post(LLAMA_VISION_API_URL, json=payload)
        response.raise_for_status()  # Raise an error for bad responses
        return response.json()  # Assuming the response is JSON

@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket):
    await websocket.accept()
    try:
        while True:
            # Receive image from the frontend
            data = await websocket.receive_text()
            # Decode base64 image
            image_data = base64.b64decode(data)
            nparr = np.frombuffer(image_data, np.uint8)
            frame = cv2.imdecode(nparr, cv2.IMREAD_COLOR)

            # Convert frame to base64
            _, buffer = cv2.imencode('.jpg', frame)
            encoded_image = base64.b64encode(buffer).decode('utf-8')

            # Send the image to the Llama vision model asynchronously
            llama_response = await send_image_to_llama(encoded_image)

            # Send the response back to the UI
            await websocket.send_text(llama_response)  # Send the response as a string

    except Exception as e:
        print(f"Error: {e}")
    finally:
        await websocket.close()

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)
