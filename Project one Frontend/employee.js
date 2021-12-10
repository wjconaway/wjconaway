
window.addEventListener('load', async () => {

    let res = await fetch('http://localhost:8080/checklogin', {
        credentials: 'include',
        method: 'GET'
    });

    if (res.status === 200){
        let userObj = await res.json();

        if (userObj.userroll === 'manager'){
            window.location.href = 'manager-home.html';
        }
    } else if (res.status === 401) {
        window.location.href = 'index.html';
    }
    reimbursementTable();
});

async function reimbursementTable() {
    let res = await fetch('http://localhost:8080/reimbursements', {
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
        td2.innerHTML = reimbursement.amount
        
       // let td3 = document.createElement('td');
       // td3.innerHTML = reimbursement.submitted
        
       // let td4 = document.createElement('td');
       // td4.innerHTML = reimbursement.resolved
        
        let td5 = document.createElement('td');
        td5.innerHTML = reimbursement.status
       
        let td6 = document.createElement('td');
        td6.innerHTML = reimbursement.type
        
        let td8 = document.createElement('td');
        td8.innerHTML = reimbursement.author
        
        let td9 = document.createElement('td');
        td9.innerHTML = reimbursement.resolver
        if (reimbursement.resolver != 0){
          td5.innerHTML = "Complete";
          td9.innerHTML = reimbursement.resolver;
      } else {
          td5.innerHTML = 'pending';
          td9.innerHTML = '-';
      }
      let td10 = document.createElement('td');
      let viewImageButton = document.createElement('button');
        viewImageButton.innerHTML = 'View Image';
        td10.appendChild(viewImageButton);

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
      });

        tr.appendChild(td1);
        tr.appendChild(td2);
        // tr.appendChild(td3);
        // tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td8);
        tr.appendChild(td9);
        tr.appendChild(td10);


        tbodyElement.appendChild(tr);
    }
}




let logoutBtn = document.querySelector('#logout');

logoutBtn.addEventListener('click', async () => {
    let res = await fetch('http://localhost:8080/logout',{
        'method': 'POST',
        'credentials': 'include'
    });

    if (res.status === 200){
        window.location.href = 'index.html';
    }
})

Array.prototype.forEach.call(
    document.querySelectorAll(".file-upload__button"),
    function(button) {
      const hiddenInput = button.parentElement.querySelector(
        ".file-upload__input"
      );
      const label = button.parentElement.querySelector(".file-upload__label");
      const defaultLabelText = "No file(s) selected";
  
      // Set default text for label
      label.textContent = defaultLabelText;
      label.title = defaultLabelText;
  
      button.addEventListener("click", function() {
        hiddenInput.click();
      });
  
      hiddenInput.addEventListener("change", function() {
        const filenameList = Array.prototype.map.call(hiddenInput.files, function(
          file
        ) {
          return file.name;
        });
  
        label.textContent = filenameList.join(", ") || defaultLabelText;
        label.title = label.textContent;
      });
    }
  );

let reimbursementSubmitButton = document.querySelector('#submit-reimbursement-btn');

reimbursementSubmitButton.addEventListener('click', submitReimbursement);

async function submitReimbursement() {
  let reimbursementNameInput = document.querySelector('#reimbursement-name');
  let reimbursementNameInput1 = document.querySelector('#amount');
  
  let reimbursementImageInput = document.querySelector('#reimbursement-file');
  
  const file = reimbursementImageInput.files[0];
  
  let formData = new FormData();
  formData.append('reimbursement_name', reimbursementNameInput.value);
  formData.append('amount', reimbursementNameInput1.value);
  
  formData.append('receipt_image', file);

  let res = await fetch('http://localhost:8080/reimbursements', {
    method: 'POST',
    credentials: 'include',
    body: formData

  });

  if (res.status === 201){
    reimbursementTable();
  } else if (res.status === 400){
      let reimbursementForm = document.querySelector('#reimbursement-submit-form');

      let data = await res.json();

      let pTag = document.createElement('p')
      pTag.innerHTML = data.message;

      reimbursementForm.appendChild(pTag);
  }
}
