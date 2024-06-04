import React, { useEffect } from "react";
import s from "./home.module.css";

const handleLogout = () => {
	localStorage.removeItem("token");
	window.location.href = "/";
}

const Home = () => {
	return (
		<div className={s.home}>
			<div className={s.welcome_box}>
				<div className={s.message}>
					<h1>Welcome</h1>
					<h2>to the attendance management system</h2>
				</div>
				<button>Get Started</button>
			</div>
			<button className={s.log_out} onClick={handleLogout}>Log out</button>
		</div>
	);
}

export default Home;