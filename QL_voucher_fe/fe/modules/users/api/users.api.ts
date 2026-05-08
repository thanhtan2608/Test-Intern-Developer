import { UsersRequest, UsersResponse } from "../types/users.type";

const BASE_URL = "http://localhost:8080/api/users";

export const usersApi = {
  // Lấy danh sách user
  getAll: async (): Promise<UsersResponse[]> => {
    const response = await fetch(BASE_URL);
    if (!response.ok) throw new Error("Không thể lấy danh sách người dùng");
    return response.json();
  },

  // Tạo user mới
  create: async (data: UsersRequest): Promise<UsersResponse> => {
    const response = await fetch(BASE_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error("Lỗi khi tạo người dùng");
    return response.json();
  },
};