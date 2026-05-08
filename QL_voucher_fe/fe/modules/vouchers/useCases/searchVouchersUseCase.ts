import { vouchersApi } from "../api/vouchers.api";

export const searchVoucherUseCase = async (code: string) => {
  return await vouchersApi.searchByCode(code);
};