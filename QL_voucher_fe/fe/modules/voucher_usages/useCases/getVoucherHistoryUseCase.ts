import { voucherUsagesApi } from "../api/voucher_usages.api";

export const getVoucherHistoryUseCase = async () => {
  try {
    const history = await voucherUsagesApi.getHistory();
    // Bạn có thể thêm logic sắp xếp ngày mới nhất ở đây
    return history.sort((a, b) => 
      new Date(b.usedAt).getTime() - new Date(a.usedAt).getTime()
    );
  } catch (error) {
    throw error;
  }
};