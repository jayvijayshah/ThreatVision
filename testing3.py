import httpx
import base64

def send_image_to_together(image_base64):
    # Define the Together API URL
    url = "https://api.together.xyz/v1/completions"

    # Set up headers with your Authorization token
    headers = {
        "Authorization": "Bearer e088d119387844fb80a70a76432c00f00f4bdc9c5eb9944b426916398b323e33",
        "Content-Type": "application/json"
    }

    # Construct the payload as a dictionary
    payload = {
        "model": "meta-llama/Llama-3.2-11B-Vision-Instruct-Turbo",
        "messages": [
            {
                "role": "user",
                "content": [
                    {
                        "type": "text",
                        "text": "Please evaluate the provided image based on the accompanying prompt and return the following strictly in JSON format:\r\n\r\njson\r\n\r\n{\r\n    \"threat_level\": number,  \/\/ A number between 0 and 100.\r\n    \"crime_identified\": {   \/\/ A single crime or potential risk.\r\n        \"crime\": \"string\",  \/\/ Name of the crime.\r\n        \"description\": \"string\"  \/\/ Description of the identified risk, limited to 2 lines maximum.\r\n    }\r\n}\r\nThreat level: A number between 0 and 100, categorized as:\r\n0\u201320: Minimal threat, safe environment.\r\n21\u201340: Low threat, minor potential risks.\r\n41\u201360: Moderate threat, noticeable risks present.\r\n61\u201380: High threat, significant risks, potentially dangerous.\r\n81\u2013100: Critical threat, extreme danger, immediate action required.\r\nCrime identified: Identify one visible danger, potential risk, or hazardous element in the image (e.g., robbery, assault, vandalism, harassment, loitering, rioting, hate crimes, and prostitution), with the description limited to a maximum of two lines.\r\nNote: Pay special attention to non-physical threats such as harassment or unwanted physical contact that might not immediately appear dangerous but still indicate a significant risk. These should be considered in determining the overall threat level."
                    },
                    {
                        "type": "image_url",
                        "image_url": {
                            "url": f"data:image/jpeg;base64,{image_base64}"
                        }
                    }
                ]
            }
        ],
        "temperature": 0,
        "top_p": 0.7,
        "top_k": 50,
        "repetition_penalty": 1,
        "stream": False
    }

    # Send the POST request synchronously
    response = httpx.post(url, headers=headers, json=payload)

    # Check and return the response
    if response.status_code == 200:
        return response.json()
    else:
        return {"error": response.status_code, "message": response.text}

# Example usage:
# Assume you have an image encoded in base64
api_key = "YOUR_API_KEY"
image_path = "path/to/your/image.jpg"

# Convert image to base64
with open(image_path, "rb") as img_file:
    image_base64 = base64.b64encode(img_file.read()).decode("utf-8")

# Call the function and print the result
result = send_image_to_together(api_key, image_base64)
print(result)