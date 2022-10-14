import React from 'react'

function AddSprint({handleSubmit,sprintname,sprintdetail,onChange,onSubmit}) {
    return (
        <>
            <div>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <input
                        name="sprintname"
                        placeholder="스프린트 이름"
                        onChange={onChange}
                        value={sprintname}
                    />
                    <input
                        name="sprintdetail"
                        placeholder="스프린트 상세"
                        onChange={onChange}
                        value={sprintdetail}
                    />
                    <input type="submit"/>
                </form>
            </div>
        </>

    )
}

export default AddSprint;
