/* References: 
  - Adding a map using JS: https://leafletjs.com/
*/

var map = L.map('map').setView([53.1927993, -2.889644], 13);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

var circle = L.circle([53.1927993, -2.889644], {
    color: 'red',
    fillColor: '#f03',
    fillOpacity: 0.5,
    radius: 500
}).addTo(map);

