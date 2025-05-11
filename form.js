// Function to Generate Blog Content
async function generateBlog() {
    const title = document.getElementById("blogTitle").value.trim();
  
    // Check if the title is provided
    if (!title) {
      alert("Please enter a blog idea or title.");
      return;
    }
  
    // Disable the button and show 'Generating...' while the API request is being processed
    const generateButton = document.getElementById("generateButton");
    generateButton.disabled = true;
    generateButton.textContent = "Generating...";
  
    try {
      // Fetch request to the backend to generate blog content
      const response = await fetch('/api/generateBlog', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title })
      });
  
      // If the response is not ok, show an error
      if (!response.ok) {
        throw new Error("Failed to generate blog.");
      }
  
      // Parse the response data and display the generated content in the textarea
      const data = await response.json();
      document.getElementById("blogContent").value = data.content || "No content generated.";
  
    } catch (error) {
      // Show an error alert if something goes wrong
      alert("Error: " + error.message);
    } finally {
      // Re-enable the button and reset the text
      generateButton.disabled = false;
      generateButton.textContent = "Generate";
    }
  }
  
  // Function to Publish Blog
  async function publishBlog() {
    const title = document.getElementById("blogTitle").value.trim();
    const content = document.getElementById("blogContent").value.trim();
  
    // Ensure title and content are provided before publishing
    if (!title || !content) {
      alert("Title and content are required.");
      return;
    }
  
    try {
      // Send the blog content to the backend to be saved
      const response = await fetch('/api/publishBlog', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title, content })
      });
  
      // If the response is not ok, show an error
      if (!response.ok) {
        throw new Error("Failed to publish blog.");
      }
  
      // Show a success message and clear the inputs
      alert("Blog Published!");
      document.getElementById("blogTitle").value = "";
      document.getElementById("blogContent").value = "";
  
    } catch (error) {
      // Show an error alert if publishing fails
      alert("Error: " + error.message);
}
  }