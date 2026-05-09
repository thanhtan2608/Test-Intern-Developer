"use client";

import React, { useEffect, useState } from 'react';
import { History, User, Ticket, Calendar, Search, RefreshCcw, ArrowLeftRight } from 'lucide-react';
import { VoucherUsageResponse } from '@/modules/voucher_usages/types/voucher_usages.type';
import { getVoucherHistoryUseCase } from '@/modules/voucher_usages/useCases/getVoucherHistoryUseCase';

export default function VoucherHistoryPage() {
  const [history, setHistory] = useState<VoucherUsageResponse[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');

  const fetchHistory = async () => {
    setIsLoading(true);
    try {
      const data = await getVoucherHistoryUseCase();
      setHistory(data || []);
    } catch (error) {
      console.error("Lỗi khi tải lịch sử:", error);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchHistory();
  }, []);

  const filteredHistory = history.filter(item => 
    item.userName.toLowerCase().includes(searchTerm.toLowerCase()) ||
    item.voucherCode.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="min-h-screen bg-gray-50 p-4 md:p-10 font-sans text-gray-800">
      <div className="max-w-6xl mx-auto">
        
        {/* Header Section */}
        <div className="flex flex-col md:flex-row md:items-center justify-between mb-8 gap-4">
          <div>
            <div className="flex items-center gap-2 mb-1">
              <History className="text-indigo-600" size={24} />
              <h1 className="text-3xl font-extrabold text-gray-900 tracking-tight">Lịch Sử Sử Dụng</h1>
            </div>
            <p className="text-gray-500">Theo dõi danh sách các voucher đã được người dùng áp dụng thành công.</p>
          </div>
          
          <button 
            onClick={fetchHistory}
            className="flex items-center justify-center gap-2 bg-white hover:bg-gray-50 text-gray-700 px-5 py-2.5 rounded-xl border border-gray-200 shadow-sm transition-all active:scale-95"
          >
            <RefreshCcw size={18} className={isLoading ? "animate-spin" : ""} />
            <span className="font-medium">Làm mới</span>
          </button>
        </div>

        {/* Search Bar */}
        <div className="mb-6 relative">
          <div className="absolute inset-y-0 left-0 pl-4 flex items-center pointer-events-none">
            <Search size={18} className="text-gray-400" />
          </div>
          <input 
            type="text"
            placeholder="Tìm theo tên khách hàng hoặc mã voucher..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="w-full pl-11 pr-4 py-3 bg-white border border-gray-100 rounded-2xl shadow-sm focus:ring-2 focus:ring-indigo-500 outline-none transition-all"
          />
        </div>

        {/* Table Container */}
        <div className="bg-white rounded-3xl shadow-sm border border-gray-100 overflow-hidden">
          <div className="overflow-x-auto">
            <table className="w-full text-left border-collapse">
              <thead>
                <tr className="bg-gray-50/50">
                  <th className="px-6 py-5 text-xs font-bold text-gray-500 uppercase tracking-wider text-center w-20">ID</th>
                  <th className="px-6 py-5 text-xs font-bold text-gray-500 uppercase tracking-wider">Người sử dụng</th>
                  <th className="px-6 py-5 text-xs font-bold text-gray-500 uppercase tracking-wider text-center">Hành động</th>
                  <th className="px-6 py-5 text-xs font-bold text-gray-500 uppercase tracking-wider">Mã Voucher</th>
                  <th className="px-6 py-5 text-xs font-bold text-gray-500 uppercase tracking-wider">Thời gian</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-100">
                {isLoading ? (
                  <tr>
                    <td colSpan={5} className="px-6 py-20 text-center">
                      <div className="flex flex-col items-center gap-2">
                        <RefreshCcw size={32} className="animate-spin text-indigo-500" />
                        <p className="text-gray-400 font-medium">Đang tải dữ liệu...</p>
                      </div>
                    </td>
                  </tr>
                ) : filteredHistory.length > 0 ? (
                  filteredHistory.map((item) => (
                    <tr key={item.id} className="hover:bg-indigo-50/20 transition-colors group">
                      <td className="px-6 py-4 text-center text-gray-400 font-mono text-sm">#{item.id}</td>
                      <td className="px-6 py-4">
                        <div className="flex items-center gap-3">
                          <div className="w-9 h-9 bg-emerald-100 text-emerald-700 rounded-full flex items-center justify-center">
                            <User size={16} />
                          </div>
                          <span className="font-bold text-gray-900">{item.userName}</span>
                        </div>
                      </td>
                      <td className="px-6 py-4 text-center">
                         <div className="inline-flex items-center justify-center p-2 bg-gray-100 rounded-lg text-gray-400 group-hover:text-indigo-500 transition-colors">
                            <ArrowLeftRight size={14} />
                         </div>
                      </td>
                      <td className="px-6 py-4">
                        <div className="flex items-center gap-2">
                          <Ticket size={16} className="text-indigo-500" />
                          <span className="font-mono font-bold px-2.5 py-1 bg-indigo-50 text-indigo-700 rounded-lg border border-indigo-100">
                            {item.voucherCode}
                          </span>
                        </div>
                      </td>
                      <td className="px-6 py-4 text-sm text-gray-500">
                        <div className="flex items-center gap-2">
                          <Calendar size={14} />
                          {new Date(item.usedAt).toLocaleString('vi-VN')}
                        </div>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={5} className="px-6 py-20 text-center text-gray-400 italic">
                      Chưa có lịch sử sử dụng nào được ghi nhận.
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>

        {/* Footer Summary */}
        {!isLoading && (
          <div className="mt-4 text-right">
            <p className="text-sm text-gray-500">
              Hiển thị <span className="font-bold text-gray-900">{filteredHistory.length}</span> lượt sử dụng
            </p>
          </div>
        )}
      </div>
    </div>
  );
}