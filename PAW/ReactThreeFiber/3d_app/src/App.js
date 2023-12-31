import React, { useRef } from 'react';
import { Canvas, useFrame } from '@react-three/fiber';
import * as THREE from 'three';
import { OrbitControls } from '@react-three/drei';
import checker from './checker.png';
import brick from './brick.png';
  

// Funkcja pomocnicza do generowania losowych wartości
function getRandomValue(minValue, maxValue) {
  return Math.floor(Math.random() * (maxValue - minValue + 1)) + minValue;
}

// Komponent reprezentujący pojedynczy obiekt 3D
const CustomObject = ({ type, attributes }) => {
  const mesh = useRef();

  useFrame(() => {
    mesh.current.rotation.x += 0.01;
    mesh.current.rotation.y += 0.01;
    mesh.current.rotation.z += 0.01;
  });

  const geometry = new THREE[type](
    attributes.width,
    attributes.height,
    attributes.depth,
    attributes.widthSegments || 1,
    attributes.heightSegments || 1,
    attributes.depthSegments || 1
  );

  const texture = new THREE.TextureLoader().load(brick);
  const material = new THREE.MeshPhongMaterial({
    map: texture,
    depthTest: true,
  });

  return <mesh geometry={geometry} material={material} ref={mesh} position={[attributes.position.x, attributes.position.y, attributes.position.z]} />;
};

// Funkcja pomocnicza do tworzenia obiektów
function createObjects(numberOfObjects) {
  const objects = [];

  for (let i = 0; i < numberOfObjects; i++) {
    const attributes = createAttributes();
    const type = i % 2 === 0 ? 'BoxGeometry' : 'SphereGeometry';
    objects.push(<CustomObject key={i} type={type} attributes={attributes} />);
  }

  return objects;
}

// Funkcja pomocnicza do generowania atrybutów obiektów
function createAttributes() {
  const minValue = -2;
  const maxValue = 2;
  const randomColor = Math.floor(Math.random() * 16777215);

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

const App = () => {
  return (
    <Canvas style={{width: "100%", height: "100vh"}}>
      {/* Kamera */}
      <perspectiveCamera
        position={[0, 0, 0]}
        fov={75}
        aspect={window.innerWidth / window.innerHeight}
        near={0.1}
        far={100}
      />

      {/* Scena */}
      <ambientLight color={0xff000aa} intensity={1} />
      <directionalLight color={0xffffff} intensity={1.5} position={[-1, 2, 4]} />
      <pointLight color={0xff0000} intensity={2} position={[0, 5, 0]} />

      {/* Obiekty */}
      {createObjects(50)}

      <OrbitControls />


      {/* Podstawa */}
      <mesh rotation-x={-Math.PI * 0.5} position-y={-5}>
        <planeGeometry args={[40, 40]} />
        <meshPhongMaterial map={new THREE.TextureLoader().load(checker)} side={THREE.DoubleSide} />
      </mesh>
    </Canvas>
  );
};

export default App;