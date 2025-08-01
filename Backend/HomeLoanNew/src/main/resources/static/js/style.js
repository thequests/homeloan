document.querySelector("form").addEventListener("submit", function(e) {
  e.preventDefault();

  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value;

  if (!email || !password) {
    alert("Please enter both email and password.");
    return;
  }

  console.log("Sending login request with:", { email, password });

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
    console.log("Response status:", response.status);
    return response.json(); // Always parse JSON, even on error
  })
  .then(data => {
    console.log("Response data:", data);

    if (data.status === "success") {
      alert(data.message || "Login successful!");
      localStorage.setItem("userEmail", email);
      localStorage.setItem("userRole", data.role); // Optional: store role
      window.location.href = "home-dashboard.html";
    } else {
      alert(data.message || "Invalid credentials.");
    }
  })
  .catch(error => {
    console.error("Fetch error:", error);
    alert("Login failed: " + error.message);
  });
});
