document.querySelector("form").addEventListener("submit", function(e) {
  e.preventDefault();

  const email = document.getElementById("email").value.trim();
  const newPassword = document.getElementById("newPassword").value.trim();

  if (!email || !newPassword) {
    alert("Please enter both email and new password.");
    return;
  }

  fetch("http://localhost:8083/user/reset-password", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ email, newPassword })
  })
  .then(response => {
    if (!response.ok) {
      throw new Error("Failed to reset password.");
    }
    return response.text(); // Since your controller returns a plain String
  })
  .then(message => {
    alert(message); // e.g., "Password reset successfully"
  })
  .catch(error => {
    console.error("Error:", error);
    alert("Something went wrong. Please try again.");
  });
});
