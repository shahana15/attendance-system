import React, { useState } from "react";
import s from "./log-in.module.css";
import axios from "axios";
function LogIn() {
	const [username, setUserName] = useState("");
	const [password, setPassword] = useState("");

	const handleSubmit = (event) => {
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