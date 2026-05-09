import { voucherUsagesApi } from "../api/voucher_usages.api";
import { VoucherUsageRequest } from "../types/voucher_usages.type";

export const useVoucherUseCase = async (data: VoucherUsageRequest) => {
  try {
    return await voucherUsagesApi.useVoucher(data);
  } catch (error) {
    throw error;
  }
};