'use strict';

function onPictureClick(e) {
    const svg = document.querySelector('svg');
    const svgInputX = document.getElementById('pictureForm:svgInputX');
    const svgInputY = document.getElementById('pictureForm:svgInputY');
    const checkButton = document.getElementById('pictureForm:checkButton');

    const rect = svg.getBoundingClientRect();
    const x = 0.02 * (e.x - rect.left - 300);
    const y = -0.02 * (e.y - rect.top - 300);
    svgInputX.value = x;
    svgInputY.value = y;
    checkButton.click();
}
