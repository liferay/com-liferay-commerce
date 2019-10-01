import React from 'react'

function Default(props) {
    if(props.value instanceof Object) {
        return (
        <>{Object.values(props.value)[0]}</>
        )
    }
    return (
        <>{props.value}</>
    )
}

export default Default;