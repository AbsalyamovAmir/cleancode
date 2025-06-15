package ru.cleancode.locksmodule.service;

public interface AccountService {

    void transferMoneyWithoutLock(Long accountId, double amount);

    void transferMoneyWithOptimisticLock(Long accountId, double amount);

    void transferMoneyWithPessimisticLock(Long accountId, double amount);
}
