$(async function () {

    await infoUser()

})
let isUser = true
const userFetch = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    findUserByName: async () => await fetch(`api/user`)
}

const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
let rolesList ;
async function infoUser() {
    let infoString = '';
    const info = document.querySelector('#info');
    await userFetch.findUserByName()
        .then(res => res.json())
        .then(user => {
            infoString += `
             <span>
               with roles <span>${roleToString(user.roles)}</span>
                </div>
            </span>
                </tr>
            `;
        });
    info.innerHTML = infoString;
    let tblString = '';
    const table = document.querySelector('#userTableContent');
    await userFetch.findUserByName()
        .then(res => res.json())
        .then(user => {
            tblString = `
                <tr>
                    <td>${user.id}</td>                   
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.age}</td>
                    <td>${roleToString(user.roles)}</td>
                </tr>
            `;
            table.innerHTML = tblString;

            $(function (){
                let role = ""
                for (let i = 0; i < user.roles.length; i++) {
                    role = user.roles[i].name
                    if (role == "ROLE_ADMIN") {
                        isUser = false;
                        getAdminPage()
                    }
                }
                if (isUser) {
                    document.querySelector('#userPageLink').classList.add('active')
                    document.querySelector('#adminMainContent').classList.add('d-none')
                    document.querySelector('#userMainContent').classList.remove('d-none')
                } else {
                    showAdminPage()

                }
            })
        })
}

fetch("api/roleList").then(res => res.json()).then(res => rolesList = res);
async function getAdminPage() {
    // document.querySelector('#adminPageLink').removeAttribute('href')
    // document.querySelector('#userPageLink').removeAttribute('href')

    const usersList = document.querySelector('#adminPageTable tbody')
    let result = ''
    console.log("ВЫВОД ОСНОВНОЙ ТАБЛИЦЫ ЮЗЕРОВ")
    await  fetch('api/userList')
        .then(res => res.json())
        .then(users => users.forEach(user => {
            console.log(user)
            result += `
                <tr>

                        <th scope="row">${user.id}</th>
                        <td >${user.name}</td>
                        <td >${user.surname}</td>
                        <td >${user.age}</td>                      
                        <td >${roleToString(user.roles)}</td>
                        
                        <td >
                            <!---------------------------------------- Modal For Update ------------------------------->
                            <div class="modal fade" id='updateUserModal${user.id}' tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="ModalLabel">Edit User</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                    <input style="display:none" id="usrid" name="id" value="${user.id}"  >
                                                    <label for="usrname" class="form-label">Name</label>
                                                    <input name="name" value="${user.name}" id="usrname" class="form-control"  ></div >
                                                 <div class="mb-3">   <label for="usrsurname" class="form-label">Secondname</label>
                                                    <input name="surname" value="${user.surname}" id="usrsurname" class="form-control" ></div >                                                 
                                                    <div class="mb-3">   <label for="usrage" class="form-label">Age</label>
                                                    <input type="number" value="${user.age}" name="age" id="usrage" class="form-control" ></div >                                               
                                                <div class="mb-3">   <label for="usrpass" class="form-label">Password</label>
                                                    <input name="age" value="${user.password}" id="usrpass" class="form-control" type="password" ></div >
                                                <div class="mb-3">   <label for="usrroles" class="form-label">Roles</label>
                                                    <select class="form-select" id="usrroles" multiple aria-label="multiple select example">
                                                        
                                                    </select></div >
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <button type="button" data-bs-dismiss="modal" onclick="updateUser('#updateUserModal${user.id}')" 
                                                class="btn btn-primary">Update User</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <button class="btn btn-success" data-bs-toggle="modal" data-bs-target='#updateUserModal${user.id}'>Edit User</button>
                        </td>
                        <td >
                            <!---------------------------------------- Modal For delete ------------------------------->
                            <div class="modal fade" id='deleteUserModal${user.id}'  tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="ModalLabel">Edit User</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                    <input style="display:none" name="id" value="${user.id}">
                                                    <label for="usrname" class="form-label">Name</label>
                                                    <input disabled name="name" value="${user.name}" id="usrname" class="form-control" ></div >
                                                <div class="mb-3">   <label for="usrsurname" class="form-label">Secondname</label>
                                                    <input disabled name="age" value="${user.surname}" id="usrsurname" class="form-control" /></div >                                               
                                                    <div class="mb-3">   <label for="usrage" class="form-label">Age</label>
                                                    <input type="number" disabled name="age" value="${user.age}" id="usrage" class="form-control" /></div>                                               
                                                <div class="mb-3">   <label for="usrpass" class="form-label">Password</label>
                                                    <input disabled name="age" value="${user.password}" id="usrpass" class="form-control" type="password" /></div >
                                                <div class="mb-3">   <label for="usrroles" class="form-label">Roles</label>
                                                    <select disabled class="form-select" id="usrroles" multiple aria-label="multiple select example">
                                                                      
                                                    </select></div >
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <button type="button" data-bs-dismiss="modal" onclick="deleteUser(${user.id})" 
                                                class="btn btn-primary btn-danger">Delete User</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target='#deleteUserModal${user.id}'>Delete User</button>
                        </td>
                    </tr>
            `
        }))
    usersList.innerHTML = result;
    $('select').empty()
    rolesList.forEach(role => {
        $('select').append($('<option>').val(role.id).text(role.name.substring(5)));
    })
    let triggerFirstTabEl = document.querySelector('#home-tab')
    new bootstrap.Tab(triggerFirstTabEl).show()
}
async function addNewUser(formIdSelector) {

        let addUserForm = $(formIdSelector)
        let firstName = addUserForm.find('#name').val().trim();
        let lastName = addUserForm.find('#surname').val().trim();
        let age = addUserForm.find('#age').val().trim();
        let password = addUserForm.find('#password').val().trim();
        let rolesArray = addUserForm.find('#userroles').val()
        let roles = []

        for (let r of rolesList) {
            for (let i = 0; i < rolesArray.length; i++) {
                if (r.id == rolesArray[i]) {
                    roles.push(r)
                }
            }
        }

        const data = {
            name: firstName,
            surname: lastName,
            age: age,
            password: password,
            roles: roles
        }

        fetch('/api/addUser', {
            credentials: 'include',
            method: 'POST',
            mode: 'cors',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': csrfToken
            },
            body: JSON.stringify(data)
        })
            .then(console.log)
            .then(x=> getAdminPage());

}

async function deleteUser(id){
    let data = {
        id: id
    }
    fetch('/api/deleteUser', {
        credentials: 'include',
        method: 'delete',
        mode: 'cors',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(data)
    })
        .then(console.log)
        .then(x=>  getAdminPage());

}

async function updateUser(formIdSelector) {

    let addUserForm = $(formIdSelector)
    let id = addUserForm.find('#usrid').val().trim();
    let firstName = addUserForm.find('#usrname').val().trim();
    let lastName = addUserForm.find('#usrsurname').val().trim();
    let age = addUserForm.find('#usrage').val().trim();
    let password = addUserForm.find('#usrpass').val().trim();
    let rolesArray = addUserForm.find('#usrroles').val()
    let roles = []

    for (let r of rolesList) {
        for (let i = 0; i < rolesArray.length; i++) {
            if (r.id == rolesArray[i]) {
                roles.push(r)
            }
        }
    }

    let data = {
        id: id,
        name: firstName,
        surname: lastName,
        age: age,
        password: password,
        roles: roles

    }

    fetch('/api/editUser', {
        credentials: 'include',
        method: 'PUT',
        mode: 'cors',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(data)
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error('Error occurred!')
            }
            return response.json()
        })
        .then(console.log)
        .then(x=>  getAdminPage());
}

function roleToString(userRoles) {
    let htmlString = ''
    for (let i = 0; i < userRoles.length; i++) {
        let arr = userRoles[i].name.split('_');
        if (i > 0) {
            htmlString += " " + arr[1]
        } else {
            htmlString += arr[1]
        }
    }
    return htmlString
}

async function showUserPage() {
    document.querySelector('#adminPageLink').classList.remove('active')
    document.querySelector('#adminPageTabs').classList.add('d-none')
    document.querySelector('#userPageLink').classList.add('active')
    document.querySelector('#adminMainContent').classList.add('d-none')
    document.querySelector('#userMainContent').classList.remove('d-none')
}

async function showAdminPage() {
    document.querySelector('#adminPageLink').classList.add('active')
    document.querySelector('#adminPageTabs').classList.remove('d-none')
    document.querySelector('#userPageLink').classList.remove('active')
    document.querySelector('#userMainContent').classList.add('d-none')
    document.querySelector('#adminMainContent').classList.remove('d-none')
}