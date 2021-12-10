window.addEventListener('load', async () => {

    let res = await fetch('http://localhost:8080/checklogin', {
        credentials: 'include',
        method: 'GET'
    });

    if  (res.status === 200){
        let userObj = await res.json();

        if (userObj.userroll === 'worker'){
            window.location.href = 'employee-home.html';
        }
    } else if (res.status === 401) {
        window.location.href = 'index.html';
    }

    reimbursementTable();
});

async function reimbursementTable() {
    let res = await fetch('http://localhost:8080/reimbursement', {
        credentials: 'include',
        method: 'GET'
    });

    let tbodyElement = document.querySelector("#reimbursement-table tbody");
    tbodyElement.innerHTML = '';
    let reimbursArray = await res.json();

    for (let i = 0; i < reimbursArray.length; i++){
        let reimbursement = reimbursArray[i];

        let tr = document.createElement('tr');

        let td1 = document.createElement('td');
        td1.innerHTML = reimbursement.id
        
        let td2 = document.createElement('td');
        td2.innerHTML = reimbursement.firstname
        
       let td3 = document.createElement('td');
       td3.innerHTML = reimbursement.lastname
        
       let td4 = document.createElement('td');
       td4.innerHTML = reimbursement.amount
        
        let td5 = document.createElement('td');
        
       
        let td6 = document.createElement('td');
        td6.innerHTML = reimbursement.type
        
        let td7 = document.createElement('td');
        td7.innerHTML = reimbursement.author
        
        let td8 = document.createElement('td');
        let td11 = document.createElement('td');
        td11.innerHTML = reimbursement.resolver;

        let td9 = document.createElement('td');
        let td10 = document.createElement('td');
        if (reimbursement.resolver != 0){
            td5.innerHTML = "Complete";
            td11.innerHTML = reimbursement.resolver;
        } else {
            td5.innerHTML = 'pending';
            td11.innerHTML = '-';
        
            let statusInput = document.createElement('input');
            statusInput.setAttribute('type', 'string');
            
            let approvalButton = document.createElement('button');
            approvalButton.innerText = 'Approve/Deny';
            approvalButton.addEventListener('click', async () => {
            
                let res = await fetch('http://localhost:8080/reimbursements/${reimbursement.id}/status', 
                {
                    credentials: 'include',
                    method: 'PATCH',
                    body: JSON.stringify({
                        status: statusInput.value   
                    })
                });
            if (res.status === 200){
                reimbursementTable();
            }
        })

        let td12 = document.createElement('td');

        let viewImageButton = document.createElement('button');
        viewImageButton.innerHTML = 'View Image';
        td8.appendChild(viewImageButton);

        viewImageButton.addEventListener('click', () => {
        
        let reimbursementImageModal = document.querySelector('#reimbursement-image-modal');

            
        let modalCloseElement = reimbursementImageModal.querySelector('button');
        modalCloseElement.addEventListener('click', () => {
            reimbursementImageModal.classList.remove('is-active');
        });

        let modalContentElement = reimbursementImageModal.querySelector('.modal-content');
        modalContentElement.innerHTML = '';

        let imageElement = document.createElement('img');
        imageElement.setAttribute('src', `http://localhost:8080/reimbursements/${reimbursement.id}/image`);
        modalContentElement.appendChild(imageElement);

            reimbursementImageModal.classList.add('is-active'); // add a class to the modal element to have it display
        })


        td9.appendChild(approvalButton);
        td10.appendChild(statusInput)


    }

    

    let td12 = document.createElement('td');
    //td13.innerHTML = reimbursement.approvalButton;

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);
        tr.appendChild(td10);
        // tr.appendChild(td11);
        // tr.appendChild(td12);

        tbodyElement.appendChild(tr);
    }
}




let logoutBtn = document.querySelector('#logout');

logoutBtn.addEventListener('click', async () => {
    let res = await fetch('http://localhost:8080/logout',{
        'method': 'POST',
        'credentials': 'include'
    });

    if (res.status === 200) {
        window.location.href = 'index.html';
    }
})