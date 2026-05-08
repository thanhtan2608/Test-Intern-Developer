import { usersApi } from "../api/users.api";
import { UsersResponse } from "../types/users.type";

export const getAllUsersUseCase = async (): Promise<UsersResponse[]> => {
  try {
    return await usersApi.getAll();
  } catch (error) {
    console.error("UseCase Error:", error);
    throw error;
  }
};