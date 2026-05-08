import { vouchersApi } from "../api/vouchers.api";
import { VoucherRequest } from "../types/vouchers.type";

export const deleteVoucherUseCase = async (id: number) => {
  return await vouchersApi.delete(id);
};