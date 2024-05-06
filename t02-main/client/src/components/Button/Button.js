import React from "react"

const Button = ({handleClick, label}) => (
    <div>
        <button onClick={handleClick}>{label}</button>
    </div>
);

export default Button;