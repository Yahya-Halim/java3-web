<main>
  <div class="container py-4">
    <header class="pb-3 mb-4 border-bottom text-center">
      <h1 class="fw-bold">My Profile</h1>
    </header>

    <div class="p-5 mb-4 bg-primary text-white rounded-3 text-center">
      <form action="upload" method="post" enctype="multipart/form-data">
        <img src="${profile.image}" alt="Profile Picture" class="rounded-circle mb-3" width="150" height="150">
        <input type="file" name="profileImage" class="form-control mb-3">
        <button type="submit" class="btn btn-light">Upload</button>
      </form>
      <h2>${profile.name}</h2>
      <p class="fs-4">Software Developer | AI Enthusiast | Game Developer</p>
    </div>

    <div class="row align-items-md-stretch">
      <div class="col-md-6">
        <div class="h-100 p-5 bg-light border rounded-3">
          <h2>About Me</h2>
          <form action="updateProfile" method="post">
            <textarea name="aboutMe" class="form-control mb-3">${profile.aboutMe}</textarea>
            <button type="submit" class="btn btn-primary">Save</button>
          </form>
        </div>
      </div>
      <div class="col-md-6">
        <div class="h-100 p-5 text-bg-dark rounded-3">
          <h2>Contact</h2>
          <form action="updateProfile" method="post">
            <input type="text" name="email" class="form-control mb-2" value="${profile.email}">
            <input type="text" name="phone" class="form-control mb-2" value="${profile.phone}">
            <button type="submit" class="btn btn-light">Save</button>
          </form>
        </div>
      </div>
    </div>

    <footer class="pt-3 mt-4 text-center text-body-secondary border-top">
      &copy; 2024 Yahya Halim
    </footer>
  </div>
</main>