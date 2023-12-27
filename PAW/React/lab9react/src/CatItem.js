import React, { useState } from 'react';

function CatItem({ cat }) {
  const [isClicked, setIsClicked] = useState(false);

  const handleClick = () => {
    setIsClicked(!isClicked);
  };

  return (
    <div className={`cat-item ${isClicked ? 'clicked' : ''}`} onClick={handleClick}>
      <img src={cat.url} alt="Cat" />
    </div>
  );
}

export default CatItem;
