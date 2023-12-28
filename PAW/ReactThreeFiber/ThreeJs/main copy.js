import * as THREE from './node_modules/three/build/three.module.js';
// PARAMETRY
var windowwidth = window.innerWidth; // Szerokość okna.
var windowHeight = window.innerHeight; // Wysokość okna.
var fieldofView = 75; // Kąt widzenia kamery.
var aspectRatio = window.innerWidth / window.innerHeight; // Współczynnik proporcji kamery. 
var nearPlane = 0.1; // Minimalne pole widzenia kamery. 
var farPlane = 100; // Maksymalne pole widzenia kamer.

// 1. Utworzenie sceny.
const scene = new THREE.Scene();

// 2. Utworzenie kamery.
const camera = new THREE.PerspectiveCamera(
  fieldofView, aspectRatio, nearPlane, farPlane
);

// 3. Utworzenie renderera.
const renderer = new THREE.WebGLRenderer();
renderer.setSize(windowwidth, windowHeight);
document.body.appendChild(renderer.domElement);

// 4. Utworzenie obiektów do rysowania.
const geometry = new THREE.BoxGeometry(2, 2, 2); // GEOMETRIA
const material = new THREE.MeshBasicMaterial({color: 0xffffff, wireframe: true}); // METERIAŁ 
const cube = new THREE.Mesh(geometry, material); // GEOMETRIA + MATERIAŁ 

scene.add(cube)

// 5. Animacja
camera.position.z = 5;
function animateScene()
{
  cube.rotation.x += 0.01;
  cube.rotation.y += 0.01;
  cube.rotation.z += 0.01;

  requestAnimationFrame(animateScene)
  renderer.render(scene, camera)
}
animateScene()