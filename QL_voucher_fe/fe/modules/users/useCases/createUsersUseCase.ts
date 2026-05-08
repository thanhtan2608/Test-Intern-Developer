import { usersApi } from "../api/users.api";
import { UsersRequest, UsersResponse } from "../types/users.type";

export const createUsersUseCase = async (data: UsersRequest): Promise<UsersResponse> => {
  try {
    // Bạn có thể thêm logic validation dữ liệu ở đây trước khi gọi API
    if (!data.email.includes("@")) {
      throw new Error("Email không hợp lệ");
    }
    
    return await usersApi.create(data);
  } catch (error) {
    console.error("UseCase Error:", error);
    throw error;
  }
};