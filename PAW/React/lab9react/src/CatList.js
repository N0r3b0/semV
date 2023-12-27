import React, { useState, useEffect } from 'react';
import axios from 'axios';
import CatItem from './CatItem';

function CatList() {
  const [catList, setCatList] = useState([]);

  useEffect(() => {
    // Pobierz dane z API przy zamontowaniu komponentu
    axios.get('https://api.thecatapi.com/v1/images/search?limit=2')
      .then(response => {
        setCatList(response.data);
      })
      .catch(error => {
        console.error('Error fetching cat data:', error);
      });
  }, []); // Pusta tablica oznacza, że useEffect zostanie uruchomiony tylko raz przy montowaniu

  return (
    <div>
      <h2>Cats</h2>
      {catList.map(cat => (
        <CatItem key={cat.id} cat={cat} />
      ))}
    </div>
  );
}

export default CatList;
