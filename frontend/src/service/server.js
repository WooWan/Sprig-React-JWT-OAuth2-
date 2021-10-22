import axios from "axios";

class Server {
    constructor(server) {
        this.server = server;
    }
    setupAxiosInterceptors(){
        this.server.interceptors.request.use(
            config =>{
                const token = localStorage.getItem("ACCESS_TOKEN");
                const refreshToken = localStorage.getItem("REFRESH_TOKEN");
                if (token) {
                    config.headers['Authorization'] = 'Bearer ' + token;
                    config.headers['RefreshToken'] = refreshToken;
                }
                config.headers["Content-Type"] = "application/json; charset=utf-8";
                console.log(config)
                return config;
            }
        )
    }
    async getUserList(){
        const response = await this.server.get('/api/user');
        console.log(response)
        return response;
    }

}

export default Server;