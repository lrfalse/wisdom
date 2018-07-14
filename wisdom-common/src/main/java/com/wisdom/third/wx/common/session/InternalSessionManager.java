package com.wisdom.third.wx.common.session;

public interface InternalSessionManager {

  
  InternalSession findSession(String id);

  public InternalSession createSession(String sessionId);

  public void remove(InternalSession session);

  public void remove(InternalSession session, boolean update);

  void add(InternalSession session);


  int getActiveSessions();

  InternalSession createEmptySession();

  InternalSession[] findSessions();

  public void backgroundProcess();

  void setMaxInactiveInterval(int interval);

  /**
   * <pre>
   * Set the manager checks frequency.
   * 设置每尝试多少次清理过期session，才真的会执行一次清理动作
   * 要和{@link #setBackgroundProcessorDelay(int)}联合起来看
   * 如果把这个数字设置为6（默认），那么就是说manager要等待 6 * backgroundProcessorDay的时间才会清理过期session
   * </pre>
   * @param processExpiresFrequency the new manager checks frequency
   */
  void setProcessExpiresFrequency(int processExpiresFrequency);

  /**
   * <pre>
   * Set the manager background processor delay
   * 设置manager sleep几秒，尝试执行一次background操作（清理过期session）
   * </pre>
   * @param backgroundProcessorDelay
   */
  void setBackgroundProcessorDelay(int backgroundProcessorDelay);


  /**
   * Set the maximum number of active Sessions allowed, or -1 for
   * no limit.
   * 设置最大活跃session数，默认无限
   * @param max The new maximum number of sessions
   */
  void setMaxActiveSessions(int max);

}
