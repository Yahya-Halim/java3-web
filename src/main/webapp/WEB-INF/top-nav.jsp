<style>
  .nav-gradient {
    background: linear-gradient(to right, #007bff, #6a11cb);
  }
</style>

<div class="container-fluid">
  <header class="d-flex flex-wrap justify-content-between align-items-center py-3 nav-gradient shadow">
    <a href="${appURL}" class="d-flex align-items-center ms-3 text-white text-decoration-none">
      <img src="./styles/TFF_Logo.png" alt="Logo" width="32" height="32" class="me-2">
      <span class="fs-4">THE FIVE BRo'S</span>
    </a>

    <ul class="nav">
      <li><a href="${appURL}" class="nav-link px-3 text-white">Home</a></li>
      <li><a href="#" class="nav-link px-3 text-white">Features</a></li>
      <li><a href="#" class="nav-link px-3 text-white">Pricing</a></li>
      <li><a href="${appURL}/users" class="nav-link px-3 text-white">Users</a></li>
      <li><a href="#" class="nav-link px-3 text-white">About</a></li>
    </ul>

    <div class="me-3">
      <button type="button" class="btn btn-outline-light me-2">Login</button>
      <a href="${appURL}/signup" class="btn btn-light">Sign-up</a>
    </div>
  </header>
</div>
