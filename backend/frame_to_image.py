import cv2
import sys
import time

def extract_frame_as_image(video_path, frame_number):
    # Open the video file
    video_capture = cv2.VideoCapture(video_path)

    if not video_capture.isOpened():
        raise ValueError(f"Cannot open video file: {video_path}")

    # Get the total number of frames in the video
    total_frames = int(video_capture.get(cv2.CAP_PROP_FRAME_COUNT))
    if frame_number >= total_frames:
        raise ValueError(f"Frame number {frame_number} is out of bounds (total frames: {total_frames})")

    # Set the video to the specified frame
    video_capture.set(cv2.CAP_PROP_POS_FRAMES, frame_number)

    # Read the frame
    success, frame = video_capture.read()
    
    if not success:
        raise IOError(f"Could not read frame at position: {frame_number}")

    # Convert the frame (BGR) to RGB format
    frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

    # Release the video capture
    video_capture.release()

    return frame

def save_image(frame, output_path):
    # Save the frame as an image file
    cv2.imwrite(output_path, frame)

def main():
    if len(sys.argv) != 4:
        print("Usage: python extract_frame.py <video_path> <frame_number> <output_path>")
        sys.exit(1)

    video_path = sys.argv[1]
    frame_number = int(sys.argv[2])
    output_path = sys.argv[3]

    start_time = time.time()  # Start time for performance measurement

    try:
        # Extract frame
        frame_image = extract_frame_as_image(video_path, frame_number)
        # Save the extracted frame as an image file
        save_image(frame_image, output_path)
        #print(f"Frame extracted and saved as '{output_path}' in {time.time() - start_time:.2f} seconds")
        print("success")
    except Exception as e:
        print(f"Error: {e}")

if __name__ == '__main__':
    main()

