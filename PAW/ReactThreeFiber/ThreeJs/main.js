import * as THREE from './node_modules/three/build/three.module.js';
import { OrbitControls } from 'three/addons/controls/OrbitControls.js';

// PARAMETRY
var windowWidth = window.innerWidth; // Szerokosc okna.
var windowHeight = window.innerHeight; // Wysokosc okna.
var fieldOfView = 75; // Kat widzenia kamery.
var aspectRatio = window.innerWidth / window.innerHeight; // Wspoiczynnik proporcji kamery.
var nearplane = 0.1; // Minimalne pole widzenia kamery.
var farplane = 100; // Maksymalne pole widzenia kamery.

// 1. Utworzenie sceny.
const scene = new THREE.Scene();

// 2. Utworzenie kamery.
const camera = new THREE.PerspectiveCamera(
  fieldOfView,
  aspectRatio,
  nearplane,
  farplane,
);

// 3. Utworzenie renderera.
const renderer = new THREE.WebGLRenderer();
renderer.setSize(windowWidth, windowHeight);
document.body.appendChild(renderer.domElement);

// 4. Utworzenie obiektów do rysowania.
function getRandomValue(minValue, maxValue) {
  return Math.floor(Math.random() * (maxValue - minValue + 1)) + minValue;
}

function createGeometryAndMaterial(type, attributes) {
  const geometry = new THREE[type](
    attributes.width,
    attributes.height,
    attributes.depth,
    attributes.widthSegments || 1,
    attributes.heightSegments || 1,
    attributes.depthSegments || 1
  );

  const texture = new THREE.TextureLoader().load('brick.png');
  const material = new THREE.MeshPhongMaterial({
    map: texture,
    depthTest: true,
  });

  return new THREE.Mesh(geometry, material);
}

function createObjects(objects, numberOfObjects) {
  for (let i = 0; i < numberOfObjects; i++) {
    const attributes = createAttributes();
    let object;

    if (i % 2 === 0) {
      // Create cube
      object = createGeometryAndMaterial('BoxGeometry', attributes);
    } else {
      // Create sphere
      object = createGeometryAndMaterial('SphereGeometry', attributes);
    }

    object.position.set(
      attributes.position.x,
      attributes.position.y,
      attributes.position.z
    );

    objects.push(object);
  }
}

function addObjectsToScene(objects, scene) {
  objects.forEach(object => {
    scene.add(object);
  });
}

function createAttributes() {
  var minValue = -2;
  var maxValue = 2;
  var randomColor = Math.floor(Math.random() * 16777215);
  return {
    width: getRandomValue(minValue, maxValue) / 2,
    height: getRandomValue(minValue, maxValue) / 2,
    depth: getRandomValue(minValue, maxValue) / 2,
    color: randomColor,
    position: {
      x: getRandomValue(minValue, maxValue),
      y: getRandomValue(minValue, maxValue),
      z: getRandomValue(minValue, maxValue),
    },
  };
}

var objects = [];
var numberOfObjects = 50;
createObjects(objects, numberOfObjects);
addObjectsToScene(objects, scene);

// Dodanie różnych typów świateł

// 1. Kierunkowego
const directionalColor = 0xffffff;
const directionalIntensity = 1.5;
const directionalLight = new THREE.DirectionalLight(
  directionalColor,
  directionalIntensity,
);
directionalLight.position.set(-1, 2, 4);
scene.add(directionalLight);

// 2. Zawsze obecnego
const ambientColor = 0xff000aa;
const ambientIntensity = 1;
const ambientLight = new THREE.AmbientLight(ambientColor, ambientIntensity);
scene.add(ambientLight);

// 3. Punktowe
const pointColor = 0xff0000;
const pointIntensity = 2;
const pointLight = new THREE.PointLight(pointColor, pointIntensity);
pointLight.position.set(0, 5, 0);
scene.add(pointLight);

// Ruch myszką
const controls = new OrbitControls(camera, renderer.domElement);
controls.target.set(0, 5, 0);

// Dodanie Podstawki
const planeSize = 40;
const loader = new THREE.TextureLoader();
const texture = loader.load('checker.png');
texture.wrapS = THREE.RepeatWrapping;
texture.wrapT = THREE.RepeatWrapping;
texture.magFilter = THREE.NearestFilter;
texture.colorSpace = THREE.SRGBColorSpace;
const repeats = planeSize / 2;
texture.repeat.set(repeats, repeats);

// Utworzenie geometrii wraz z materiałem
const planeGeo = new THREE.PlaneGeometry(planeSize, planeSize);
const planeMat = new THREE.MeshPhongMaterial({
  map: texture,
  side: THREE.DoubleSide,
});

// Utworzenie siatki
const planeMesh = new THREE.Mesh(planeGeo, planeMat);
planeMesh.rotation.x = Math.PI * -0.5;
planeMesh.position.y = -5;
scene.add(planeMesh);

// 5. Animacja
camera.position.z = 5;
function animateScene() {
  objects.forEach(object => {
    object.rotation.x += 0.01;
    object.rotation.y += 0.01;
    object.rotation.z += 0.01;
  });

  requestAnimationFrame(animateScene);
  renderer.render(scene, camera);
}

animateScene();
