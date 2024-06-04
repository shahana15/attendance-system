import React, { useState } from "react";
import s from "./log-in.module.css";
import axios from "axios";
function LogIn() {
	const [username, setUserName] = useState("");
	const [password, setPassword] = useState("");

	const handleSubmit = (event) => {
		event.preventDefault();
		if (username === "" || password === "") {
			alert("Please enter both username and password");
			return;
		}

		const user = { username, password };
		axios.post("http://localhost:8081/api/v1/auth/login", user).then(response => {
			console.log("RESPONSE ", response);
			if (response.status == 200) {
				localStorage.setItem("token", response.data.data.access_token);
				window.location.href = "/home";
			}
		}).catch((error) => {
			if (error.response.data.status === 401) {
				alert("Invalid username or password");
			}
		});
	};
	return (
		<div className={s.log_in}>
			<form className={s.form}>
				<h1>Log in</h1>
				<input
					className={s.text_input}
					placeholder="Username"
					type="text"
					value={username}
					onChange={(e) => setUserName(e.target.value)}
					required
				/>

				<input
					className={s.text_input}
					placeholder="Password"
					type="password"
					value={password}
					onChange={(e) => setPassword(e.target.value)}
					required
				/>
				<button onClick={handleSubmit}>Log in</button>
			</form>
		</div>
	);
}

export default LogIn;