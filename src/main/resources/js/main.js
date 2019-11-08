function onPictureClick(e) {
    const svg = document.querySelector('svg');
    const rect = svg.getBoundingClientRect();
    const x = (e.x - rect.left - 300) / 50;
    const y = (e.y - rect.top) / 50;
    console.log(x, y);
}
