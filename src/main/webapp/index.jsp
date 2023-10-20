<%@include file="head.jsp"%>
<html>
<body>

<div class="container">
    <h2 class="text-center">User Display Exercise</h2>
    <form action="searchUser" method="get">
        <div class="input-group p-3 m-3">
            <div class="input-group-text">
                <label>
                    <input class="form-check-input" type="radio" name="searchType" value="first_name">
                </label>
            </div>
            <div class="input-group-text">
                <label>
                    <input class="form-check-input" type="radio" name="searchType" value="last_name">
                </label>
            </div>
            <div class="input-group-text">
                <label>
                    <input class="form-check-input" type="radio" name="searchType" value="user-name">
                </label>
            </div>
            <div class="input-group-text">
                <label>
                    <input class="form-check-input" type="radio" name="searchType" value="id">
                    Search by ID</label>
            </div>
            <div class="input-group-text">
                <button class="btn btn-outline-secondary" type="submit" name="submit" value="viewAll">View all</button>
            </div>

            <div class="w-100 mt-3">
                <p>Enter search term:</p>
                <div class="input-group w-50">
                    <input class="form-control" type="text" name="searchTerm">
                    <button class="btn btn-outline-secondary" type="submit" name="submit" value="search">Submit</button>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</body>
</html>