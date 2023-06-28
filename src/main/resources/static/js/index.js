function showPopup(popupId) {
  let popup = document.getElementById(popupId);
  popup.style.display = "block";
}

// Function to handle form submission
function submitForm(popupId) {
  let popup = document.getElementById(popupId);
  let inputs = popup.getElementsByTagName("input");
  let username = inputs[0].value;
  let password = inputs[1].value;

  if (popupId === "signUpPopup") {
    // Perform validation for username field
    if (username.trim() === "") {
      alert("Username cannot be empty.");
      return;
    }

    // Perform validation for password field
    if (password.trim() === "") {
      alert("Password cannot be empty.");
      return;
    }
  }

  // Add your form submission logic here using username and password

  // Clear input fields
  inputs[0].value = "";
  inputs[1].value = "";

  // Hide the popup
  popup.style.display = "none";
}