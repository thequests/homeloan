document.getElementById("createAccountForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const firstName = document.getElementById("firstName").value.trim();
  const lastName = document.getElementById("lastName").value.trim();
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirmPassword").value;

  const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;

  if (password !== confirmPassword) {
    alert("Passwords do not match.");
    return;
  }

  if (!passwordRegex.test(password)) {
    alert("Password must contain at least 8 characters with uppercase, lowercase, number, and special character.");
    return;
  }

  const userData = {
    firstName,
    lastName,
    email,
    passwordHash: password
  };

  fetch("http://localhost:8083/user/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(userData)
  })
  .then(response => {
    if (!response.ok) {
      throw new Error("Registration failed.");
    }
    return response.text(); // Your backend returns a plain String
  })
  .then(message => {
    alert(message); // e.g., "User registered successfully"
    localStorage.setItem("firstName", firstName);
    window.location.href = "home-dashboard.html";
  })
  .catch(error => {
    console.error("Error:", error);
    alert("Something went wrong. Please try again.");
  });
});
