import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.jsx";
import "./index.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import LogIn from "./pages/log-in/log-in.jsx";
import Home from "./pages/home/home.jsx";

// const router = createBrowserRouter([
// 	{
// 		element: <App />,
// 		children: [
// 			{
// 				path: "/",
// 				element: <Home />,
// 			},
// 			{
// 				path: "/log-in",
// 				element: <LogIn />,
// 			},
// 		],
// 	},
// ]);
const router = createBrowserRouter([
	{
		element: <App />,
		children: [
			{
				path: "/",
				element: <LogIn />,
			},
			{
				path: "/Home",
				element: <Home />,
			},
		],
	},
]);
ReactDOM.createRoot(document.getElementById("root")).render(
	<React.StrictMode>
		<RouterProvider router={router} />
	</React.StrictMode>
);