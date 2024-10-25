from fastapi import FastAPI, Request
from fastapi.responses import HTMLResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates
import uvicorn

app = FastAPI()

# Mount static files - add this line
app.mount("/static", StaticFiles(directory="."), name="static")

# Templates configuration
templates = Jinja2Templates(directory="templates")

# Sample data - replace with your actual data
threat_data = {
    "current": 75,
    "max": 100
}

logs_data = [
    {"timestamp": "2024-10-25 10:00:00", "message": "System started"},
    {"timestamp": "2024-10-25 10:01:00", "message": "Processing threat #1"},
]

@app.get("/", response_class=HTMLResponse)
async def read_root(request: Request):
    return templates.TemplateResponse("index.html", {"request": request})

@app.get("/api/threat-status")
async def get_threat_status():
    return threat_data

@app.get("/api/logs")
async def get_logs():
    return logs_data

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)