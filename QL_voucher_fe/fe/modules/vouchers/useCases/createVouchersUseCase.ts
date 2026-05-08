import { vouchersApi } from "../api/vouchers.api";
import { VoucherRequest } from "../types/vouchers.type";

export const createVoucherUseCase = async (data: VoucherRequest) => {
  return await vouchersApi.create(data);
};