'use strict';
let svg, svgInputX, svgInputY, checkButton;

window.addEventListener('load', function() {
    svg = document.querySelector('svg');
    svgInputX = document.getElementById('pictureForm:svgInputX');
    svgInputY = document.getElementById('pictureForm:svgInputY');
    checkButton = document.getElementById('pictureForm:checkButton');

    svg.addEventListener('click', onPictureClick);
});

function onPictureClick(e) {
    const rect = svg.getBoundingClientRect();
    const x = 0.02 * (e.x - rect.left - 300);
    const y = -0.02 * (e.y - rect.top - 300);
    svgInputX.value = x;
    svgInputY.value = y;
    checkButton.click();
}
