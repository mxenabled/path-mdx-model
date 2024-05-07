package com.mx.path.model.mdx.model.remote_deposit;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;

public final class Limits extends MdxBase<Limits> {

  @SerializedName("daily_deposit_limit_current")
  private Double dailyDepositLimitCurrent;

  @SerializedName("daily_deposit_limit_total")
  private Double dailyDepositLimitTotal;

  @SerializedName("weekly_deposit_limit_current")
  private Double weeklyDepositLimitCurrent;

  @SerializedName("weekly_deposit_limit_total")
  private Double weeklyDepositLimitTotal;

  @SerializedName("monthly_deposit_limit_current")
  private Double monthlyDepositLimitCurrent;

  @SerializedName("monthly_deposit_limit_total")
  private Double monthlyDepositLimitTotal;

  public Double getDailyDepositLimitCurrent() {
    return dailyDepositLimitCurrent;
  }

  public void setDailyDepositLimitCurrent(Double dailyDepositLimitCurrent) {
    this.dailyDepositLimitCurrent = dailyDepositLimitCurrent;
  }

  public Double getDailyDepositLimitTotal() {
    return dailyDepositLimitTotal;
  }

  public void setDailyDepositLimitTotal(Double dailyDepositLimitTotal) {
    this.dailyDepositLimitTotal = dailyDepositLimitTotal;
  }

  public Double getWeeklyDepositLimitCurrent() {
    return weeklyDepositLimitCurrent;
  }

  public void setWeeklyDepositLimitCurrent(Double weeklyDepositLimitCurrent) {
    this.weeklyDepositLimitCurrent = weeklyDepositLimitCurrent;
  }

  public Double getWeeklyDepositLimitTotal() {
    return weeklyDepositLimitTotal;
  }

  public void setWeeklyDepositLimitTotal(Double weeklyDepositLimitTotal) {
    this.weeklyDepositLimitTotal = weeklyDepositLimitTotal;
  }

  public Double getMonthlyDepositLimitCurrent() {
    return monthlyDepositLimitCurrent;
  }

  public void setMonthlyDepositLimitCurrent(Double monthlyDepositLimitCurrent) {
    this.monthlyDepositLimitCurrent = monthlyDepositLimitCurrent;
  }

  public Double getMonthlyDepositLimitTotal() {
    return monthlyDepositLimitTotal;
  }

  public void setMonthlyDepositLimitTotal(Double monthlyDepositLimitTotal) {
    this.monthlyDepositLimitTotal = monthlyDepositLimitTotal;
  }
}
