document.getElementById("adminLoginForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const email = document.getElementById("adminEmail").value.trim();
  const password = document.getElementById("adminPassword").value;

  if (!email || !password) {
    alert("Please enter both email and password.");
    return;
  }

  fetch("http://localhost:8083/user/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      email: email,
      passwordHash: password
    })
  })
  .then(response => {
    if (!response.ok) {
      throw new Error("Login failed.");
    }
    return response.json();
  })
  .then(data => {
    // Example: check if user is admin
    if (data.role === "ADMIN" || email === "admin@homeloan.com") {
      alert("Welcome Admin!");
      localStorage.setItem("adminEmail", email);
      window.location.href = "admin-dashboard.html";
    } else {
      alert("Access denied. You are not an admin.");
    }
  })
  .catch(error => {
    console.error("Error:", error);
    alert("Invalid credentials or server error.");
  });
});
