let fileUpload = document.getElementById('file');
let preview = document.querySelector('.preview');

const fileTypes = [
    'image/jpeg',
    'image/gif',
    'image/png'
];

fileUpload.addEventListener("change", updateImageDisplay);

function returnFileSize(number) {
    if (number < 1024) {
        return number + 'bytes';
    } else if (number > 1024 && number < (1024 * 1024)) {
        return (number / 1024).toFixed(1) + 'KB';
    } else if (number > (1024 * 1024)) {
        return (number / (1024 * 1024)).toFixed(1) + 'MB';
    }
}

function checkFileSize(number) {
    return number < 2 * 1024 * 1024;
}

function validFileType(file) {
    for (let i = 0; i < fileTypes.length; i++) {
        if (file.type === fileTypes[i]) {
            return true;
        }
    }
    return false;
}

function updateImageDisplay() {
    while (preview.firstChild) {
        preview.removeChild(preview.firstChild);
    }
    let curFiles = fileUpload.files;
    if (curFiles.length === 0) {
        let para = document.createElement('p');
        para.textContent = 'No files currently selected for upload';
        preview.appendChild(para);
    } else {
        let list = document.createElement('ol');
        preview.appendChild(list);
        for (let i = 0; i < curFiles.length; i++) {
            let listItem = document.createElement('li');
            let para = document.createElement('p');
            if (validFileType(curFiles[i]) && checkFileSize(curFiles[i].size)) {
                para.textContent = 'File name ' + curFiles[i].name + ', file size ' + returnFileSize(curFiles[i].size) + '.';
                let image = document.createElement('img');
                image.src = window.URL.createObjectURL(curFiles[i]);

                listItem.appendChild(image);
                listItem.appendChild(para);

            } else {
                para.textContent = `File name ${curFiles[i].name}, ${returnFileSize(curFiles[i].size)}: 
                Not a valid file type or size.`;
                para.style.color = "red";
                listItem.appendChild(para);
            }

            list.appendChild(listItem);
        }
    }
}