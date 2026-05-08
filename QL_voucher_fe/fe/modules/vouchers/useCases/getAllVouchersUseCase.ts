import { vouchersApi } from "../api/vouchers.api";

export const getAllVouchersUseCase = async () => {
  return await vouchersApi.getAll();
};