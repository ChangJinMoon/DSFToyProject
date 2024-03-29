//아직 적용 x
import React from "react"

const Modal=(props)=>{
  const {open, close, header, postdata}=props;

  return (
      // 모달이 열릴때 openModal 클래스가 생성된다.
      <div className={open ? 'openModal modal' : 'modal'}>
        {open ? (
            <section>
              <header>
                {header}
                <button className="close" onClick={close}>
                  &times;
                </button>
              </header>
              <main>{props.children}</main>
              <footer>
                <button className="close" onClick={close}>
                  close
                </button>
                <button onClick={postdata}>
                  ADD
                </button>
              </footer>
            </section>
        ) : null}
      </div>
  );
};

export default Modal;