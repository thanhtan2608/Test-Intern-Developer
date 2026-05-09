import { VoucherUsageRequest, VoucherUsageResponse } from "../types/voucher_usages.type";

const BASE_URL = "http://localhost:8080/api/voucher-usages";

export const voucherUsagesApi = {
  // POST: Thực hiện sử dụng voucher
  useVoucher: async (data: VoucherUsageRequest): Promise<string> => {
    const response = await fetch(BASE_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });
    if (!response.ok) {
      const errorMsg = await response.text();
      throw new Error(errorMsg || "Không thể sử dụng voucher");
    }
    return response.text();
  },

  // GET: Lấy lịch sử sử dụng
  getHistory: async (): Promise<VoucherUsageResponse[]> => {
    const response = await fetch(BASE_URL);
    if (!response.ok) throw new Error("Lỗi khi tải lịch sử");
    return response.json();
  }
};