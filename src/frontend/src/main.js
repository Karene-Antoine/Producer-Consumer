import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import lottie from "lottie-web";
import { defineElement } from "@lordicon/element";

const cors = require("cors")

// define "lord-icon" custom element with default properties
defineElement(lottie.loadAnimation);


createApp(App).use(router).mount('#app')
