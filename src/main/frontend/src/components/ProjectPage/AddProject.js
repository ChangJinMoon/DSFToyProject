import React from 'react'

function AddProject({projectname,projectdetail,onChange,onCreate,onSubmit,onGet}) {
  return (
    <>
    <div>
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
      <button onClick={onSubmit}>보내기</button>
      
    </div>
    <button onClick={onGet}>받기</button>
    </>
    
  )
}

export default AddProject
