import { Layout, Typography } from "antd";
import { useState } from "react";
// import "./App.css";
import FoodList from "./components/FoodList";
import LoginForm from "./components/LoginForm";
import MyCart from "./components/MyCart";
import SignupForm from "./components/SignupForm";

const { Header, Content } = Layout;
const { Title } = Typography;

function App() {
  const [authed, setAuthed] = useState(false);
  
  return (
    // vh: view height
    // jsx 代码
    // { height: "100vh" } 是 JS 代码，是一个 JS 的 object
    <Layout style={{ height: "100vh" }}>
      <Header>
        <div className = "header" style={{display: "flex", justifyContent: "space-between"}}>
          <Title
            level = {2}
            style = {{color: "white", lineHeight: "inherit", marginBottom: 0}}
          >
            Lai Food
          </Title>
          <div>{authed ? <MyCart /> : <SignupForm />}</div>
        </div>
      </Header>

      <Content
        style={{
          padding: "50px",  // 将元素内容和边框隔开
          maxHeight: "calc(100% - 64px)", // 计算 layout100% - Header64
          overflowY: "auto",  // Content 内容 超过 maxHeight 时
        }}
      >
        {authed ? (   // 是否登录
          // <div>content placeholder</div>
          <FoodList />
        ) : (
          <LoginForm onSuccess = {() => setAuthed(true)} />
        )}
      </Content>
    </Layout>
  );
}

export default App;

// import logo from './logo.svg';
// import './App.css';

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

// export default App;
