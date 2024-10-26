document.addEventListener("DOMContentLoaded", () => {
  const threatProgress = document.getElementById("threatProgress");
  const logsContent = document.getElementById("logsContent");
  const videoFileName = "bar-fight.mp4";
  const fps = 1;
  let frameNumber = 0;

  async function fetchThreatLevel() {
    frameNumber += fps;
    try {
      const response = await fetch(
        "https://a49f-61-246-51-230.ngrok-free.app/ThreatVision/api/process",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
          body: new URLSearchParams({
            name: videoFileName,
            frame_no: frameNumber,
          }),
        }
      );
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();
      updateThreatProgress(data);
      addLog(data);
    } catch (error) {
      console.error("Error fetching threat level:", error);
    }
  }

  function updateThreatProgress(data) {
    const progress = data.threat_level;
    threatProgress.style.width = `${progress}%`;
    riskLevel.textContent = `${progress}%`;

    if (progress > 50) {
      threatProgress.classList.add("danger");
      threatProgress.classList.remove("warning");
    } else if (progress > 30) {
      threatProgress.classList.add("warning");
      threatProgress.classList.remove("danger");
    } else {
      threatProgress.classList.remove("warning", "danger");
    }
  }

  function addLog(data) {
    const time = getCurrentTime();
    const type = data.threat_level > 80 ? "danger" : data.threat_level > 40 ? "warning" : "normal";
    const logEntry = document.createElement("div");
    logEntry.className = `log-entry ${type}`;
    logEntry.innerHTML = `
      <div class="icon">
          <i class="fas ${getIconForType(type)}"></i>
      </div>
      <div class="log-content">
          <div class="timestamp">${time}</div>
          New threat identified: ${data.crime_identified.crime} - ${
      data.crime_identified.description
    }
      </div>
    `;
    logsContent.insertBefore(logEntry, logsContent.firstChild);
    if (logsContent.children.length > 20) {
      logsContent.removeChild(logsContent.lastChild);
    }
  }

  function getCurrentTime() {
    return new Date().toLocaleTimeString("en-US", {
      hour12: false,
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit",
    });
  }

  function getIconForType(type) {
    switch (type) {
      case "normal":
        return "fa-info";
      case "warning":
        return "fa-exclamation-triangle";
      case "danger":
        return "fa-radiation";
      default:
        return "fa-info";
    }
  }

  setInterval(fetchThreatLevel, 1000);
});
