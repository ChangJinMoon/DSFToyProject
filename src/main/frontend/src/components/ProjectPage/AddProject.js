import React from 'react'

function AddProject({handleSubmit,projectname,projectdetail,onChange,onSubmit,onGet}) {
  return (
      <>
        <div>
          <form onSubmit={handleSubmit(onSubmit)}>
            <input
                name="projectname"
                placeholder="프로젝트 이름"
                onChange={onChange}
                value={projectname}
            />
            <input
                name="projectdetail"
                placeholder="프로젝트 상세"
                onChange={onChange}
                value={projectdetail}
            />
            <input type="submit"/>
          </form>

        </div>
      </>

  )
}

export default AddProject
