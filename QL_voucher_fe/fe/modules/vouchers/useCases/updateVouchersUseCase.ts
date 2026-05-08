import { vouchersApi } from "../api/vouchers.api";
import { VoucherRequest } from "../types/vouchers.type";

export const updateVoucherUseCase = async (id: number, data: VoucherRequest) => {
  return await vouchersApi.update(id, data);
};