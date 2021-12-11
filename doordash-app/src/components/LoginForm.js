// We need a form to collect user input and execute a login request
import { Button, Form, Input, message } from "antd";
import React from "react";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { login } from "../utils";

// 也可以用 function component 代替 class component
// 借鉴 Antd 的 Form 源码
class LoginForm extends React.Component {
  // 与 Button 的 loading 属性相连，让botton进入转圈圈的形态，同时暂时disable button 防止用户继续乱点
  state = {
    loading: false,
  };

  // 点击 Login button 后出发的事件
  // data 是 Antd 传进来的
  onFinish = (data) => {
    this.setState({
      loading: true,
    });
    login(data)
      .then(() => {
        message.success(`Login Successful`);
        this.props.onSuccess();
      })
      .catch((err) => {
        message.error(err.message);
      })
      .finally(() => {
        this.setState({
          loading: false,
        });
      });
  };

//   data's format：
// {
//     username
//     password
// }

  render = () => {
    return (
      <Form
        name="normal_login"
        onFinish={this.onFinish}
        style={{
          width: 300,
          margin: "auto",   // 水平居中
        }}
      >
        <Form.Item
          name="username"
          rules={[{ required: true, message: "Please input your Username!" }]}  //用户忘记填写时的提醒
        >
          <Input prefix={<UserOutlined />} placeholder="Username" /> 
        </Form.Item>
        <Form.Item
          name="password"
          rules={[{ required: true, message: "Please input your Password!" }]}
        >
          <Input.Password prefix={<LockOutlined />} placeholder="Password" />
        </Form.Item>

        <Form.Item>
            {/* "submit"  会触发 Form的 submit 事件 */}
          <Button type="primary" htmlType="submit" loading={this.state.loading}>
            Login
          </Button>
        </Form.Item>
      </Form>
    );
  };
}

export default LoginForm;

